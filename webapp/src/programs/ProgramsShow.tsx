import { NumberField, Show, SimpleShowLayout, TextField } from "react-admin";

type Programs = {
  name?: string;
  id: number;
  category?: string;
  subcategory?: string;
  details?: string;
  version?: number;
};

export const ProgramsShow = () => {
  return (
    <Show<Programs> resource="programs">
      <SimpleShowLayout sx={{ display: "grid" }}>
        <TextField
          source="name"
          sx={{ fontSize: "1.5rem", fontFamily: "fantasy" }}
        />
        <TextField
          source="category"
          sx={{ fontSize: "1.5rem", fontFamily: "fantasy" }}
        />
        <TextField
          source="subcategory"
          sx={{ fontSize: "1.5rem", fontFamily: "fantasy" }}
        />
        <TextField
          source="details"
          sx={{ fontSize: "1.5rem", fontFamily: "fantasy" }}
        />
        <NumberField
          source="version"
          sx={{ fontSize: "1.5rem", fontFamily: "fantasy" }}
        />
      </SimpleShowLayout>
    </Show>
  );
};
