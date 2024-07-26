import React, { useContext } from 'react';
import { useHistory } from 'react-router-dom';
import UserContext from '../Context/UserContext';
import './style.css';

export default function AuthOptions() {
	const { userData, setUserData } = useContext(UserContext);
	const history = useHistory();
	const logout = () => {
		setUserData({
			token: undefined,
			user: undefined,
		});
		localStorage.setItem('auth-token', '');
		localStorage.setItem('user', '');
		history.push('/');
	};
	const searchPage = () => {
		history.push('/searchresults');
	};

	return (
		<>
			{userData.user ? (
				<>	
					 	<button
							id='logoutbutton'
							className=" btn btn-outline-dark btn-md mr-2 float-right custom-btn"
							onClick={logout}
						>
							Logout
						</button>
						<button
							id='searchbutton'
							className=" btn btn-outline-dark btn-md mr-2 float-right custom-btn"
							onClick={searchPage}
						>
							Search
						</button>
					
				</>
			) : (
				<></>
			)}
		</>
	);
}
