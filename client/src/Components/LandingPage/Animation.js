import React from 'react';
import { lightSpeedIn } from 'react-animations';
import Radium, { StyleRoot } from 'radium';

const styles = {

    lightSpeedIn: {
        animation: 'x 10s',
        animationName: Radium.keyframes(lightSpeedIn, 'lightSpeedIn')
    }
}

export default class Animation extends React.Component {
    render() {
        return (
            <StyleRoot>
                <div className="animation-center" style={{display:'inline',position:'relative',top:'200px',left:'80px',fontFamily:'"Kode Mono", monospace'}}>
                    <h1 style={{fontSize:"70px",display:'inline'}}>DailyJournal</h1>
                    <h3>Gain the ultimate clarity<br></br>and peace of mind YOU DESERVE.</h3>
                </div>
            </StyleRoot>
        )
    }
}
