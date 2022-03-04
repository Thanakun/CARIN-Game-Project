// css
import styles from '../CSSstyle/statusBar.module.css'

// images
import statusbar from '../Images/statusBarrock.png'
import coin from '../Images/money.png'
import clock from '../Images/clock.png'
import virus from '../Images/Green Virus.png'
import antibody from '../Images/antibodyred.png'
import { useDataStore } from '../Store/DataStore'
import { useEffect } from 'react'
import Clock from './Clock'

const StatusBar = () => {
    const dataStore = useDataStore()

    

    return (
        <div className={styles.container}>
            <div className={styles.bg}><img src={statusbar} alt="" /></div>
            <div className={styles.items}>
                <div className={styles.clock}>
                    <div className={styles.icon}><Clock /></div>
                    <div className={styles.cost}><p>{dataStore.timer.time_count}</p></div>
                </div>
                <div className={styles.coin}>
                    <div className={styles.icon}><img src={coin} alt="" /></div>
                    <div className={styles.cost}><p>{dataStore.credit}</p></div>
                </div>
                <div className={styles.virus}>
                    <div className={styles.icon}><img src={virus} alt="" /></div>
                    <div className={styles.cost}><p>{dataStore.virus_amount}</p></div>
                </div>
                <div className={styles.antibody}>
                    <div className={styles.icon}><img src={antibody} alt="" /></div>
                    <div className={styles.cost}><p>{dataStore.antibody_amount}</p></div>
                </div>
            </div>
        </div>
    )
}

export default StatusBar