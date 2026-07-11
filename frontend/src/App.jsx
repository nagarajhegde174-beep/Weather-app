import { RouterProvider } from 'react-router-dom';
import router from './routes.jsx';
import 'bootstrap/dist/css/bootstrap.min.css';
import './css/global.css';

function App() {
  return <RouterProvider router={router} />;
}

export default App;
