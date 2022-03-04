import { useEffect, useState } from 'react'
import styles from '../CSSstyle/clock.module.css' 
import { useDataStore } from '../Store/DataStore'

const Clock = () => {
    // variables
   const dataStore = useDataStore()

    // Hook
    useEffect(() => {
        starter()
    },[dataStore.timer.time_count])

    // functions
    const starter = () => {
        const min = document.getElementById('long')
        const houre = document.getElementById('short')
        if (min) min.style.cssText += `transform: rotate(${Math.floor(dataStore.timer.time_count/60)*30-180}deg);`
        if (houre) houre.style.cssText += `transform: rotate(${(dataStore.timer.time_count%60)*6-180}deg);`
    }

    return (
        <div className={styles.containerClock}>
            <div id='short' className={styles.longtNeedle}></div>
            <div id='long' className={styles.shortNeedle}></div>
        </div>
    )
}

export default Clock