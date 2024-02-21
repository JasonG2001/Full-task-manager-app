import "./Task.css";
import { Edit } from "../Icons/Edit";
import { Delete } from "../Icons/Delete";
import { Complete } from "../Icons/Complete";
import { TaskI } from "../../utils/TaskI";
import { PriorityIcon } from "../Icons/PriorityIcon";
import { useState } from "react";
import { TiTick } from "react-icons/ti";

interface Props {
  task: TaskI;
  handleDelete: (taskID: number) => void;
  handleUpdate(taskId: number, newTask: Partial<TaskI>): void;
}

const styleWhenDone = (done: boolean): object => {
  return done
    ? {
        boxShadow: "inset 0.2rem 0.2rem 1rem 1rem rgb(0, 0, 0, 0.1)",
        transition: "0.5s",
      }
    : {};
};

export const Task: React.FC<Props> = ({ task, handleDelete, handleUpdate }) => {
  const [editMode, setEditMode] = useState<boolean>(false);
  const name = task.taskName;
  const priority = task.priority;
  const done = task.completed;

  const handleFinishEdit = (): void => {
    fetch(`http://localhost:8080/api/v1/tasks/${task.taskID}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        taskName: name,
        priority: priority,
        completed: done,
      }),
    }).catch((error) => console.log(error));
    setEditMode(() => false);
  };

  const handleNameChange = (value: string): void => {
    handleUpdate(task.taskID, { taskName: value });
  };
  const handlePriorityChange = (value: number): void => {
    handleUpdate(task.taskID, { priority: value });
  };

  const handleDone = (): void => {
    fetch(`http://localhost:8080/api/v1/tasks/${task.taskID}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        taskName: name,
        priority: priority,
        completed: !done,
      }),
    }).catch((error) => console.log(error));

    handleUpdate(task.taskID, { completed: !task.completed });
  };

  return (
    <div className="taskContainer" style={styleWhenDone(done)}>
      <div className="taskElements">
        {editMode ? (
          <div className="taskNameEditContainer">
            <input
              type="text"
              value={name}
              autoFocus={true}
              onChange={(e) => handleNameChange(e.target.value)}
              onKeyDown={(e) => {
                e.key === "Enter" && handleFinishEdit();
              }}
            />
            <button className="finishEdit" onClick={() => handleFinishEdit()}>
              <TiTick />
            </button>
          </div>
        ) : done ? (
          <s className="taskName">{name}</s>
        ) : (
          <div className="taskName">{name}</div>
        )}
        <div className="priorityAndIcons">
          {editMode ? (
            <input
              type="number"
              value={priority}
              onChange={(e) => handlePriorityChange(e.target.valueAsNumber)}
              min={1}
              max={10}
              className="edittedPriority"
              onKeyDown={(e) => {
                e.key === "Enter" && handleFinishEdit();
              }}
            ></input>
          ) : (
            <PriorityIcon priority={priority} />
          )}
          <div className="icons">
            <Edit setEditMode={setEditMode} />
            <Complete handleDone={handleDone} />
            <Delete taskID={task.taskID} handleDelete={handleDelete} />
          </div>
        </div>
      </div>
    </div>
  );
};
