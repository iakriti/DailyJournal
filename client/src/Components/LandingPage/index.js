import React,{ Component } from 'react';
import { Link } from 'react-router-dom';
import { Jumbotron } from 'react-bootstrap';
import Animation from './Animation';
import './style.css';

class LandingPage extends Component {
  render() {
    return (
      <>
         <Jumbotron className="JumbotronStyle" fluid="true" style={{ background:'transparent' , borderRadius: '0px', paddingLeft :'15px', paddingRight :'15px', paddingTop :'15px', paddingBottom :'16px', margin: '0px'}}>
          <div>
          <h3 className="head" style={{display:"inline",color:"white"}}>DailyJournal</h3>
            {/* Logo */}
            {/* <img className="logo" src={logo} alt="logo"></img> */}
            {/* Login Button */}
            <Link
              className="btn btn-outline-dark btn-md float-right custom-btn"
              to="/login" >
              Login
            </Link>
          </div>
        </Jumbotron>
        
        <div className="landingImg">
          <Animation />
        </div>
      </>
    );
  }
}

export default LandingPage;
