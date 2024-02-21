import { useState } from "react";
import "./AddTaskPage.css";

// Todo: add description for task

interface Props {
  addTaskPage: boolean;
  setAddTaskPage: React.Dispatch<React.SetStateAction<boolean>>;
}

export const AddTaskPage: React.FC<Props> = ({
  addTaskPage,
  setAddTaskPage,
}) => {
  const [taskName, setTaskName] = useState<string>("");
  const [description, setDescription] = useState<string>("");
  const [priority, setPriority] = useState<number>(0);

  const handleSubmit = (): void => {
    fetch("http://localhost:8080/api/v1/tasks", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        taskName,
        description,
        priority,
        completed: false,
      }),
    });
    setAddTaskPage(() => false);
    handleCancel();
  };

  const handleCancel = (): void => {
    setAddTaskPage(() => false);
    setTaskName(() => "");
    setDescription(() => "");
    setPriority(() => 0);
  };

  return addTaskPage ? (
    <div className="formContainer">
      <div className="formRows">
        <div className="row">
          <div className="formTitles">Task: </div>
          <input
            type="text"
            className="taskInput"
            onChange={(e) => setTaskName(() => e.target.value)}
          />
        </div>
        <div className="row">
          <div className="formTitles">Description: </div>
          <textarea
            maxLength={100}
            onChange={(e) => setDescription(() => e.target.value)}
          ></textarea>
        </div>
        <div className="row">
          <div className="formTitles">Priority: </div>
          <input
            type="number"
            className="priority"
            min={1}
            max={10}
            onChange={(e) => setPriority(() => e.target.valueAsNumber)}
          />
        </div>
        <div className="row">
          <button className="submit" onClick={() => handleSubmit()}>
            Submit
          </button>
          <button className="cancel" onClick={() => handleCancel()}>
            Cancel
          </button>
        </div>
      </div>
    </div>
  ) : (
    <div></div>
  );
};
