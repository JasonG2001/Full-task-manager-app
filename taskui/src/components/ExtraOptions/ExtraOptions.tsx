import { FaSortAlphaDown } from "react-icons/fa";
import { FaFilter } from "react-icons/fa6";
import "./ExtraOptions.css";

interface Props {
  title: string;
  options: string[];
  handleOption: (e: React.ChangeEvent<HTMLSelectElement>) => void;
}

const getOptionIcon = (title: string): JSX.Element => {
  switch (title) {
    case "Sort By":
      return <FaSortAlphaDown />;
    case "Filter":
      return <FaFilter />;
  }
  return <div></div>;
};

export const ExtraOptions: React.FC<Props> = ({
  title,
  options,
  handleOption,
}) => {
  return (
    <div className="optionsContainer">
      {getOptionIcon(title)} &nbsp; {title}:
      <select className="optionsBox" onChange={(e) => handleOption(e)}>
        {options.map((option, index) => (
          <option value={option} key={index}>
            {option}
          </option>
        ))}
      </select>
    </div>
  );
};
