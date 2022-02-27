// Hook
import { useEffect } from 'react'

// CSS
import styles from '../CSSstyle/logo.module.css'

// image
import Name from '../Images/name.png'
import RBC1 from '../Images/RBC_1.png'
import RBC2 from '../Images/RBC_2.png'
import RBC3 from '../Images/RBC_3.png'
import RBC4 from '../Images/RBC_4.png'
import RedVirus from '../Images/Red Virus.png'
import GreenVirus from '../Images/Green Virus.png'
import BlueVirus from '../Images/Blue Virus.png'
// import NeonVirus from '../Images/Neon Virus.png'
import GreenAntigen from '../Images/GreenAntigenv2.png'
import YellowAntigen from '../Images/YellowAnitigenv2.png'
import BlueAntigen from '../Images/BlueAntigenv2.png'
import mouse from '../Images/Mouse.png'
import tube from '../Images/tail.png'
import mucus1 from '../Images/mucus1v1.png'
import mucus2 from '../Images/mucus2V2.png'
import mucus3 from '../Images/mucus3v2.png'
import mucus4_1 from '../Images/mucus4_1.png'
import mucus4_2 from '../Images/mucus4_2.png'
import mucus4_3 from '../Images/mucus4_3.png'
import mucus4_4 from '../Images/mucus4_4.png'
import bgHomegreen from '../Images/bghome.png'

const Logo = () => {
    // Hook
    useEffect(() => {
        const bg = document.querySelector('body')
            if (bg) bg.style.cssText = `background: url(${bgHomegreen}) no-repeat fixed; width: 100%;overflow: hidden;transition: all 0.8s;`
    },[])

    return (
        <div className={styles.logocontainer}>
            <div className={styles.Vesselcontainter}>

            {/* RBC */}
            <div className={styles.RBC1container}>
                <img src={RBC1}></img>
            </div>

                {/* RBC current */}
                <div className={styles.RBCcurrent1}>
                    <img src={RBC1}></img>
                </div>

                <div className={styles.RBCcurrent2}>
                    <img src={RBC1}></img>
                </div>

                <div className={styles.RBCcurrent3}>
                    <img src={RBC1}></img>
                </div>

                <div className={styles.RBCcurrent4}>
                    <img src={RBC1}></img>
                </div>

                <div className={styles.RBCcurrent5}>
                    <img src={RBC1}></img>
                </div>

                <div className={styles.RBCcurrent6}>
                    <img src={RBC1}></img>
                </div>

                <div className={styles.RBCcurrent7}>
                    <img src={RBC1}></img>
                </div>

                <div className={styles.RBCcurrent8}>
                    <img src={RBC1}></img>
                </div>


            <div className={styles.RBC2container}>
                <img src={RBC2}></img>
            </div>

            <div className={styles.RBC3container}>
                <img src={RBC3}></img>
            </div>

            <div className={styles.RBC4container1}>
                <img src={RBC4}></img>
            </div>

            <div className={styles.RBC4container2}>
                <img src={RBC4}></img>
            </div>

            {/* Virus */}
            <div className={styles.RedVirus}>
                <img src={RedVirus}></img>
            </div>

            <div className={styles.GreenVirus}>
                <img src={GreenVirus}></img>
            </div>

            <div className={styles.BlueVirus}>
                <img src={BlueVirus}></img>
            </div>

            {/* <div className={styles.NeonVirus}>
                <img src={NeonVirus}></img>
            </div> */}

            {/* Antigen */}
            <div className={styles.GreenAntigen}>
                <img src={GreenAntigen}></img>
            </div>

            <div className={styles.YellowAntigen}>
                <img src={YellowAntigen}></img>
            </div>

            <div className={styles.BlueAntigen}>
                <img src={BlueAntigen}></img>
            </div>

            {/* Name of Game */}
            <div className={styles.Name}>
                <img src={Name}></img>
            </div>

            {/* Vessel */}
            <div className={styles.mouse}>
                <img src={mouse}></img>
            </div>

            <div className={styles.tube}>
                <img src={tube}></img>
            </div>

            <div className={styles.mucus1}>
                <img src={mucus1}></img>
            </div>

            <div className={styles.mucus2}>
                <img src={mucus2}></img>
            </div>

            <div className={styles.mucus3}>
                <img src={mucus3}></img>
            </div>

            <div className={styles.mucus4_1}>
                <img src={mucus4_1}></img>
            </div>

            <div className={styles.mucus4_2}>
                <img src={mucus4_2}></img>
            </div>

            <div className={styles.mucus4_3}>
                <img src={mucus4_3}></img>
            </div>

            <div className={styles.mucus4_4}>
                <img src={mucus4_4}></img>
            </div>
            </div>

        </div>
    )
}

export default Logo