// Hook
import { useEffect } from 'react'

// css
import styles from '../CSSstyle/loading.module.css'

// image
import buttle from '../Images/bottleCoke.png'

const Loading = () => {
  

    return (
        <div>
            <div className={styles.container}>
                <div className={styles.loader}>
                    <div className={styles.progress}></div>
                </div>
            </div>
            <div className={styles.loading}>
                <h2>Loading...</h2>
            </div>
        </div>
    )
}

export default Loading