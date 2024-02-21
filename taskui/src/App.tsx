import "./App.css";
import { TopBar } from "./components/TopBar/TopBar";
import { Tasks } from "./components/Tasks/Tasks";
import { AddTaskPage } from "./components/AddTaskPage/AddTaskPage";
import { ScreenDarkener } from "./components/ScreenDarkener/ScreenDarkener";
import { useState } from "react";

function App() {
  const [addTaskPage, setAddTaskPage] = useState<boolean>(false);
  const [sortBy, setSortBy] = useState<string>("select");
  const [filter, setFilter] = useState<string>("select");
  const handleSort = (e: React.ChangeEvent<HTMLSelectElement>): void => {
    setSortBy(() => e.target.value);
  };
  const handleFilter = (e: React.ChangeEvent<HTMLSelectElement>): void => {
    setFilter(() => e.target.value);
  };
  const handleAdd = (): void => {
    setAddTaskPage(() => true);
  };
  return (
    <div className="mainBody">
      <TopBar
        handleAdd={handleAdd}
        handleSort={handleSort}
        handleFilter={handleFilter}
      />
      <Tasks sortBy={sortBy} filter={filter} />
      <ScreenDarkener
        addTaskPage={addTaskPage}
        setAddTaskPage={setAddTaskPage}
      />
      <AddTaskPage addTaskPage={addTaskPage} setAddTaskPage={setAddTaskPage} />
    </div>
  );
}

export default App;
