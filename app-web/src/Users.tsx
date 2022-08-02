import {useEffect, useState} from "react";
import axios from "axios";


const Users = () => {
  const [users, setUsers] = useState();

  useEffect(() => {
    let isMounted = true;
    const controller = new AbortController();
    //unmount 일때 불러 오지않으려고 사용.

    const getUsers = async () => {
      try {
        const response = await axios.get('/users', {
          signal: controller.signal
          // request 취소할 때 사용 할 수 있음
        });
        console.log(response.data);
        isMounted && setUsers(response.data);
      } catch (err) {
        console.error(err);
      }
    }

    getUsers();

    return () => {
      isMounted = false;
      controller.abort();
    }

  }, [])

  return (
    <article>
      <h2>Users List</h2>
      {users?.length
        ? (
          <ul>
            {users.map((user, i) => <li key={i}>{user?.username}</li>)}
          </ul>
        ) : <p>No users to display</p>
      }
    </article>
  );
};

export default Users;
