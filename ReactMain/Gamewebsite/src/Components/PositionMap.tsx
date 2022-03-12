// Hook
import { useEffect } from 'react' 
import { useNavigate } from 'react-router-dom'
import { TransformWrapper, TransformComponent } from 'react-zoom-pan-pinch'
import ReactPlayer from 'react-player'

// Store
import { MapStore } from '../Store/MapStore'

// Store method
import { updatestatusShop } from '../Store/MapStore'

// css
import styles from '../CSSstyle/positionMap.module.css'

// components
import Shop from './Shop'
import DropbarMenu from './DropbarMenu'
import StatusBar from './StatusBar'
import GameSpleed from './GameSpeed'

// image 
import woogBox from '../Images/woodBox.png'
import woogStrikyBox from '../Images/woodstrickyBox.png'
import BgPlaying from '../Images/bgtest.png'
import woogBloodBox from '../Images/woodbloodBox.png'

type typePops = {
    x : number
    y : number
    map : string[][]
    maxScale : number
}

const positionMap = ({x, y, map, maxScale} : typePops) => {
    // Hook
    useEffect(() => {
        const bg = document.querySelector('body')
        if (bg) bg.style.cssText = `background: url(${BgPlaying}) no-repeat fixed; width: 100%;`
    })


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
            case 'WB' : return woogBloodBox;
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
            <ReactPlayer style={{display: "none"}} loop={true} playing={true} url="https://www.youtube.com/watch?v=iqtkk6bYEpk&ab_channel=player2403"/>
            <Shop/>
            <StatusBar/>
            <GameSpleed/>
            <div className={styles.containerAll} style={{top: `calc(${window.innerHeight/2}px - ${state.max_scale*state.y/2}px - 24px)`}}>
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
            </div> 
            <DropbarMenu/>
        </div>                
    )
} 

export default positionMap