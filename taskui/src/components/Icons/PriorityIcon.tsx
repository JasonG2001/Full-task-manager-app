import { Fa1 } from "react-icons/fa6";
import { Fa2 } from "react-icons/fa6";
import { Fa3 } from "react-icons/fa6";
import { Fa4 } from "react-icons/fa6";
import { Fa5 } from "react-icons/fa6";
import { Fa6 } from "react-icons/fa6";
import { Fa7 } from "react-icons/fa6";
import { Fa8 } from "react-icons/fa6";
import { Fa9 } from "react-icons/fa6";
import { Fa0 } from "react-icons/fa6";

interface Props {
  priority: number;
}

export const PriorityIcon: React.FC<Props> = ({ priority }) => {
  switch (priority) {
    case 1:
      return <Fa1 />;
    case 2:
      return <Fa2 />;
    case 3:
      return <Fa3 />;
    case 4:
      return <Fa4 />;
    case 5:
      return <Fa5 />;
    case 6:
      return <Fa6 />;
    case 7:
      return <Fa7 />;
    case 8:
      return <Fa8 />;
    case 9:
      return <Fa9 />;
    case 10:
      return (
        <>
          <Fa1 />
          <Fa0 />
        </>
      );
    default:
      return <Fa0 />;
  }
};
