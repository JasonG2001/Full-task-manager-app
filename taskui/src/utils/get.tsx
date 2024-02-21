import { TaskI } from "./TaskI";

export const get = (
  url: string,
  callback: React.Dispatch<React.SetStateAction<TaskI[] | undefined>>
): void => {
  fetch(url)
    .then((res) => res.json())
    .then((data) => {
      callback(() => data);
    })
    .catch((error) => console.log(error));
};
