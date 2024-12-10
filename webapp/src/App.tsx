import { Admin, Resource } from "react-admin";
import {
  amplicodeDarkTheme,
  amplicodeLightTheme,
} from "./themes/amplicodeTheme/amplicodeTheme";
import { dataProvider } from "./dataProvider";
import { ProgramsList } from "./programs/ProgramsList";
import { ProgramsShow } from "./programs/ProgramsShow";
import { ProgramsEdit } from "./programs/ProgramsEdit";
import { ProgramsCreate } from "./programs/ProgramsCreate";

export const App = () => {
  return (
    <Admin
      dataProvider={dataProvider}
      lightTheme={amplicodeLightTheme}
      darkTheme={amplicodeDarkTheme}
    >
      <Resource
        name="programs"
        edit={ProgramsEdit}
        list={ProgramsList}
        show={ProgramsShow}
        recordRepresentation="name"
        create={ProgramsCreate}
      />
    </Admin>
  );
};
