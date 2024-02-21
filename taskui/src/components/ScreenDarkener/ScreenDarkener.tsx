import "./ScreenDarkener.css";

interface Props {
  addTaskPage: boolean;
  setAddTaskPage: React.Dispatch<React.SetStateAction<boolean>>;
}

export const ScreenDarkener: React.FC<Props> = ({
  addTaskPage,
  setAddTaskPage,
}) => {
  return addTaskPage ? (
    <div
      className="screenDarkener"
      onClick={() => setAddTaskPage(() => false)}
    ></div>
  ) : (
    <div></div>
  );
};
