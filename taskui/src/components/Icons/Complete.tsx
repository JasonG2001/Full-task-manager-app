import "./Icon.css";
import { MdDone } from "react-icons/md";

interface Props {
  handleDone: () => void;
}

export const Complete: React.FC<Props> = ({ handleDone }) => {
  return (
    <div className="icon">
      <MdDone onClick={() => handleDone()} />
    </div>
  );
};
