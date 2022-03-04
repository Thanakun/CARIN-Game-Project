// css
import { useState } from 'react'
import styles from '../CSSstyle/gamespleed.module.css'

const GameSpleed = () => {
    // variables
    const [level,setlevel] = useState<number>(1)

    return (
        <div className={styles.container}>
            <div className={styles.plus} onClick={() => setlevel(level-1)}><span>-</span></div>
            <div className={styles.scope}><span>x{level}</span></div>
            <div className={styles.minus} onClick={() => setlevel(level+1)}><span>+</span></div>
        </div>
    )
}

export default GameSpleed