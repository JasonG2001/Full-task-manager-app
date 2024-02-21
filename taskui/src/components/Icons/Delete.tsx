import "./Icon.css";
import { RiDeleteBin6Fill } from "react-icons/ri";

interface Props {
  taskID: number;
  handleDelete: (taskID: number) => void;
}

export const Delete: React.FC<Props> = ({ taskID, handleDelete }) => {
  return (
    <div className="icon">
      <RiDeleteBin6Fill onClick={() => handleDelete(taskID)} />
    </div>
  );
};
