import "./Tasks.css";
import { Task } from "../Task/Task";
import { useEffect, useState } from "react";
import { TaskI } from "../../utils/TaskI";
import { get } from "../../utils/get";

interface Props {
  sortBy: string;
  filter: string;
}

export const Tasks: React.FC<Props> = ({ sortBy, filter }) => {
  const [allTasks, setAllTasks] = useState<TaskI[]>();
  useEffect(() => {
    get("http://localhost:8080/api/v1/tasks", setAllTasks);
  }, []);

  const handleDelete = (taskID: number): void => {
    fetch(`http://localhost:8080/api/v1/tasks/${taskID}`, {
      method: "DELETE",
      headers: {
        "Content-type": "application/json",
      },
    })
      .then(() =>
        setAllTasks((tasks) => tasks?.filter((task) => task.taskID !== taskID))
      )
      .catch((err) => console.log(err));
  };

  const handleTaskUpate = (id: number, updatedField: Partial<TaskI>) => {
    setAllTasks((tasks) =>
      tasks?.map((task) =>
        task.taskID === id
          ? {
              ...task,
              ...updatedField,
            }
          : task
      )
    );
  };

  const getVisibleTasks = (): TaskI[] | undefined => {
    let tasks = allTasks;
    switch (filter) {
      case "select":
        break;
      case "completed":
        tasks = tasks?.filter((task) => task.completed === true);
        break;
      case "incomplete":
        tasks = tasks?.filter((task) => task.completed === false);
        break;
    }
    switch (sortBy) {
      case "select":
        break;
      case "priority (ascending)":
        tasks = tasks?.sort((a, b) => a.priority - b.priority);
        break;
      case "priority (descending)":
        tasks = tasks?.sort((a, b) => b.priority - a.priority);
        break;
      case "alphabetical":
        tasks = tasks?.sort((a, b) => {
          if (a.taskName.toLowerCase() < b.taskName.toLowerCase()) {
            return -1;
          } else if (a.taskName.toLowerCase() > b.taskName.toLowerCase()) {
            return 1;
          } else {
            return 0;
          }
        });
    }

    return tasks;
  };

  const visibleTasks = getVisibleTasks();

  return (
    <div className="allTasks">
      <div className="allTasksContainer">
        {visibleTasks?.map((task: TaskI) => (
          <Task
            key={task.taskID}
            task={task}
            handleDelete={handleDelete}
            handleUpdate={handleTaskUpate}
          />
        ))}
      </div>
    </div>
  );
};
