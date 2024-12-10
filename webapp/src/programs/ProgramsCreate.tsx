import { Create, NumberInput, SimpleForm, TextInput } from "react-admin";

type Programs = {
  name?: string;
  id: number;
  category?: string;
  subcategory?: string;
  details?: string;
  version?: number;
};

export const ProgramsCreate = () => {
  return (
    <Create<Programs> resource="programs">
      <SimpleForm>
        <TextInput source="name" />
        <TextInput source="category" />
        <TextInput source="subcategory" />
        <TextInput source="details" />
        <NumberInput source="version" />
      </SimpleForm>
    </Create>
  );
};
