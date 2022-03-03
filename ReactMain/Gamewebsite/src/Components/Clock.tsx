import { useEffect, useState } from 'react'
import styles from '../CSSstyle/clock.module.css' 

const Clock = () => {
    // variables
    const [time, settime] = useState<number>(210) 

    // Hook
    useEffect(() => {
        starter()
    },[time])

    // functions
    const starter = () => {
        const min = document.getElementById('long')
        const houre = document.getElementById('short')
        if (min) min.style.cssText += `transform: rotate(${Math.floor(time/60)*30-180}deg);`
        if (houre) houre.style.cssText += `transform: rotate(${(time%60)*6-180}deg);`
    }

    return (
        <div className={styles.containerClock}>
            <div id='short' className={styles.longtNeedle}></div>
            <div id='long' className={styles.shortNeedle}></div>
        </div>
    )
}

export default Clock