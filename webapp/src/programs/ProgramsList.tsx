import {
  List,
  Datagrid,
  NumberField,
  TextField,
  EditButton,
  DeleteButton,
  TextInput,
} from "react-admin";

type Programs = {
  name?: string;
  id: number;
  category?: string;
  subcategory?: string;
  details?: string;
  version?: number
};

const filters = Array.from(
  ["nameStarts", "categoryStarts", "subcategoryStarts", "detailsStarts"],
  (s) => <TextInput key={s} source={s} />
);

export function ProgramsList() {
  return (
    <List<Programs> resource="programs" filters={filters}>
      <Datagrid rowClick="show">
        <TextField source="name" />
        <TextField source="category" />
        <TextField source="subcategory" />
        <TextField source="details" />
        <NumberField source="version" />
        <>
          <EditButton />
          <DeleteButton />
        </>
      </Datagrid>
    </List>
  );
}
