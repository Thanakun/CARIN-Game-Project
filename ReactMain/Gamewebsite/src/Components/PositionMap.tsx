// Hook
import { useNavigate } from 'react-router-dom'
import { TransformWrapper, TransformComponent } from 'react-zoom-pan-pinch'

// css
import styles from '../CSSstyle/positionMap.module.css'

// image 
import woogBox from '../Images/woodBox.png'
import woogStrikyBox from '../Images/woodstrickyBox.png'

type typePops = {
    x : number
    y : number
    map : string[][]
    maxScale : number
}

const positionMap = ({x, y, map, maxScale} : typePops) => {
    // variables
    let nav = useNavigate()

    // functions
    const createGrid =  (count : number) => {
        const output = []
        for (let i=0; i<count; i++) {
            output.push(i+1)
        }
        return output
    }

    const decoder = (key : string) => {
        switch (key) {
            case 'W' : return woogBox; 
            case 'WS' : return woogStrikyBox; 
            case 'V' : return woogBox;
            case 'A' : return woogBox;
        }
    }

    const addpath = (path : string) => {
        nav(path)
    }
 
    return (
        <div className={styles.containerAll}>
        <div className={styles.container1}>
            <div className={styles.container2}>
            <div className={styles.container3}>
            <TransformWrapper>
                <TransformComponent>
                <table className={styles.mytable}>{createGrid(y).map((i) => {
                    return(
                        <tr>{createGrid(x).map((j) => {
                            return(
                                <td style={{margin: "0",padding: "0"}}>{
                                    <img src={decoder(map[i-1][j-1])} alt="" style={{
                                        position: "relative",
                                        width: `${maxScale}px`,
                                        height: `${maxScale}px`,
                                        margin: 0
                                    }}/>} 
                                </td>
                            )
                        })}</tr>
                    )
                })}</table>
                </TransformComponent>
            </TransformWrapper>
            </div>
            </div>
        </div>
        <div>
                <a onClick={() => addpath('/pause')} className={styles.btnpuase}> 
                    <span>Pause</span>
                    <div className={styles.bthbefore}></div>
                </a>
                <a onClick={() => addpath('/shop')} className={styles.btnshop}> 
                    <span>Shop</span>
                    <div className={styles.bthbefore}></div>
                </a>
            </div>
        </div>                 
    )
} 

export default positionMap