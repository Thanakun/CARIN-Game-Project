// Hook
import { useNavigate } from 'react-router-dom'
import { TransformWrapper, TransformComponent } from 'react-zoom-pan-pinch'

// Store
import { MapStore } from '../Store/MapStore'

// Store method
import { updatestatusShop } from '../Store/MapStore'

// css
import styles from '../CSSstyle/positionMap.module.css'

// components
import Shop from './Shop'

// image 
import woogBox from '../Images/woodBox.png'
import woogStrikyBox from '../Images/woodstrickyBox.png'
import BgPlaying from '../Images/bg_gameplay.png'

type typePops = {
    x : number
    y : number
    map : string[][]
    maxScale : number
}

const positionMap = ({x, y, map, maxScale} : typePops) => {
    // variables
    let nav = useNavigate()
    const state = MapStore.useState()

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

    const openshop = (arr :number[]) => {
        const td = document.querySelectorAll('td')[arr[0]*x+arr[1]]
        td.style.cssText = "opacity: 0.5;transform: scale(0.85);"
    }

    return (
        <div>
            <img src={BgPlaying} alt="" className={styles.bg}/>
            <Shop/>
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
                                    <td>{
                                        <a onDoubleClick={(e) => {!state.shopstatus? updatestatusShop([e.pageX, e.pageY], [i-1,j-1]) : null; !state.shopstatus? openshop([i-1,j-1]) : null}}><img src={decoder(map[i-1][j-1])} alt="" style={{
                                            position: "relative",
                                            width: `${maxScale}px`,
                                            height: `${maxScale}px`,
                                            margin: 0
                                        }}/></a>} 
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
        </div>                
    )
} 

export default positionMap