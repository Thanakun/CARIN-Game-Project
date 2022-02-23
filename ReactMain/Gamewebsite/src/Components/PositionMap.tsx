// Hook
import { useNavigate } from 'react-router-dom'

// css
import styles from '../CSSstyle/positionMap.module.css'

// image 
import greenBox from '../Images/greenBox.png'
import redBox from '../Images/redBox.png'

type typePops = {
    x : number
    y : number
    map : string[][]
}

const positionMap = ({x, y, map} : typePops) => {
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
            case 'G' : return greenBox; 
            case 'R' : return redBox;
        }
    }

    const addpath = (path : string) => {
        nav(path)
    }
 
    return (
        <div>
            <div className={styles.container}>
                <table className={styles.mytable}>{createGrid(y).map((i) => {
                    return(
                        <tr>{createGrid(x).map((j) => {
                            return(
                                <td style={{margin: "0",padding: "0",}}>{
                                    <img src={decoder(map[i-1][j-1])} alt="" style={{
                                        position: "relative",
                                        width: `calc(720px/${x > y ? x : y})`,
                                        height: `calc(720px/${x > y ? x : y})`,
                                        margin: 0
                                    }}/>}
                                </td>
                            )
                        })}</tr>
                    )
                })}</table>
            </div>
            <div>
                <a onClick={() => addpath('/pause')} className={styles.btnpuase}> 
                    <span>Pause</span>
                    <div className={styles.bthbefore}></div>
                </a>
                <a onClick={() => addpath('/shop')} className={styles.btn}> 
                    <span>Shop</span>
                    <div className={styles.bthbefore}></div>
                </a>
            </div>
        </div>

    )
} 

export default positionMap