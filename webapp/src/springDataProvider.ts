import { stringify } from "query-string";
import { DataProvider, fetchUtils } from "react-admin";

export default (
  apiUrl: string,
  httpClient = fetchUtils.fetchJson
): DataProvider => ({
  getList: (resource, params) => {
    const { page, perPage } = params.pagination;
    const { field, order } = params.sort;

    const query = {
      sort: field + "," + order,
      page: page - 1,
      size: perPage,
      ...params.filter,
    };
    const url = `${apiUrl}/${resource}?${stringify(query)}`;
    return httpClient(url).then(({ json }) => parseSpringPageOrPagedModel(json));
  },

  getOne: (resource, params) =>
    httpClient(`${apiUrl}/${resource}/${params.id}`).then(({ json }) => ({
      data: json,
    })),

  getMany: (resource, params) => {
    const ids = params.ids.join(",");
    const url = `${apiUrl}/${resource}/by-ids?ids=${ids}`;
    return httpClient(url).then(({ json }) => ({ data: json }));
  },

  getManyReference: (resource, params) => {
    const { page, perPage } = params.pagination;
    const { field, order } = params.sort;

    const query = {
      sort: field + "," + order,
      page: page - 1,
      size: perPage,
      ...params.filter,
      [params.target]: params.id,
    };
    const url = `${apiUrl}/${resource}?${stringify(query)}`;
    return httpClient(url).then(({ json }) => parseSpringPageOrPagedModel(json));
  },

  update: (resource, params) =>
    httpClient(`${apiUrl}/${resource}/${params.id}`, {
      method: "PATCH",
      body: JSON.stringify(params.data),
    }).then(({ json }) => ({ data: json })),

  updateMany: (resource, params) => {
    const idsValue = params.ids.join(",");
    return httpClient(`${apiUrl}/${resource}?ids=${idsValue}`, {
      method: "PATCH",
      body: JSON.stringify(params.data),
    }).then(({ json }) => ({ data: json }));
  },

  create: (resource, params) =>
    httpClient(`${apiUrl}/${resource}`, {
      method: "POST",
      body: JSON.stringify(params.data),
    }).then(({ json }) => ({ data: json })),

  delete: (resource, params) =>
    httpClient(`${apiUrl}/${resource}/${params.id}`, {
      method: "DELETE",
      headers: new Headers({
        "Content-Type": "text/plain",
      }),
    }).then(({ json }) => ({ data: json })),

  deleteMany: (resource, params) => {
    const idsValue = params.ids.join(",");
    return httpClient(`${apiUrl}/${resource}?ids=${idsValue}`, {
      method: "DELETE",
    }).then(({ json }) => ({ data: json }));
  },

});

function parseSpringPageOrPagedModel(json: any) {
  if (json.content !== undefined) {
    let total: number | undefined;
    if (json.totalElements !== undefined){
      total = json.totalElements
    } else if (json.page?.totalElements !== undefined) {
      total = json.page.totalElements;
    }

    return {
      data: json.content,
      total,
      pageInfo: 'last' in json ? {
        hasNextPage: !json.last
      } : undefined,
    };
  } else if (Array.isArray(json)) {
    // List
    return {
      data: json,
      total: json.length,
    };
  } else {
    throw new Error("Unsupported getList() response: " + Object.keys(json));
  }
}
