import React from 'react';
import AuthOptions from '../../auth/AuthOptions';
import { Jumbotron } from 'react-bootstrap';
import logo from '../../images/logo5.jpg';
import './style.css';

function Header() {
  return (
    <Jumbotron className="JumbotronStyle" fluid="true" style={{ background:'linear-gradient(to right, #2C5364, #203A43, #0F2027)' , borderRadius: '0px', paddingLeft :'15px', paddingRight :'15px', paddingTop :'15px', paddingBottom :'16px', margin: '0px'}}>
      <div>
          <h3 style={{display:"inline",color:"white"}}>DailyJournal</h3>
      </div>
         <AuthOptions />
      
    </Jumbotron>
  );
}

export default Header;
