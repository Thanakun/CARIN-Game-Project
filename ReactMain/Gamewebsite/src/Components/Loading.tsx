// Hook
import { useEffect } from 'react'

// css
import styles from '../CSSstyle/loading.module.css'

// image
import buttle from '../Images/bottleCoke.png'

const Loading = () => {
    // Hook
    useEffect(() => {
        const bg = document.querySelector('body')
            if (bg) bg.style.cssText = `background: #1e1e1e no-repeat fixed; width: 100%;overflow: hidden;transition: all 0.8s;`
    },[])

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