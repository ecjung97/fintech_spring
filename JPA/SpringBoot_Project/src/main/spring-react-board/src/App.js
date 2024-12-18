import { Route, Routes } from "react-router-dom";
import BoardEdit from "./board/BoardEdit";
import BoardListPaging from "./board/BoardListPaging";
import BoardCont from "./board/BoardCont";
import BoardList from "./board/BoardList"; // 페이징 안되는 리액트 게시판 목록 컴포넌트 임포트
import Board_write from "./board/BoardWrite";

const App = () => {
  return (
    <Routes>
      <Route path="/" element={<BoardListPaging />} />
      <Route path="/board/:no" element={<BoardCont />} />
      <Route path="/board_write" element={<Board_write />} />
      <Route path="/board_edit/:no" element={<BoardEdit />} />
    </Routes>
  );
};

export default App;
