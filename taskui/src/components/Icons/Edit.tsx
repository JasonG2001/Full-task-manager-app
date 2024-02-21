import "./Icon.css";
import { MdEdit } from "react-icons/md";

interface Props {
  setEditMode: React.Dispatch<React.SetStateAction<boolean>>;
}

export const Edit: React.FC<Props> = ({ setEditMode }) => {
  return (
    <div className="icon">
      <MdEdit onClick={() => setEditMode(() => true)} />
    </div>
  );
};
