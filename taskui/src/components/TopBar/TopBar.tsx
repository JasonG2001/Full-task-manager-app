import "./TopBar.css";
import { IoMdAdd } from "react-icons/io";
import { ExtraOptions } from "../ExtraOptions/ExtraOptions";

interface Props {
  handleAdd: () => void;
  handleSort: (e: React.ChangeEvent<HTMLSelectElement>) => void;
  handleFilter: (e: React.ChangeEvent<HTMLSelectElement>) => void;
}

export const TopBar: React.FC<Props> = ({
  handleAdd,
  handleSort,
  handleFilter,
}) => {
  return (
    <div className="topBar">
      <div className="taskTitle">
        <div className="myTasks">My Tasks</div>
        <div className="extraOptionsContainer">
          <ExtraOptions
            title="Sort By"
            options={[
              "select",
              "priority (descending)",
              "priority (ascending)",
              "alphabetical",
            ]}
            handleOption={handleSort}
          />
          <ExtraOptions
            title="Filter"
            options={["select", "completed", "incomplete"]}
            handleOption={handleFilter}
          />
        </div>
      </div>
      <button className="addTaskButton" onClick={() => handleAdd()}>
        <IoMdAdd /> Add
      </button>
    </div>
  );
};
