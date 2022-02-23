// css
import styles from '../CSSstyle/positionMap.module.css'

// image 
import greenBox from '../Images/greenBox.png'
import redBox from '../Images/redBox.png'

type typePops = {
    x : number
    y : number
    funct1 : (e : string) => void
    path : string
    map : string[][]
}

const positionMap = ({x, y, funct1, path, map} : typePops) => {
    // variables
    
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
                <a onClick={() => funct1(path)} className={styles.btnpuase}> 
                    <span>Pause</span>
                    <div className={styles.bthbefore}></div>
                </a>
                <a onClick={() => funct1(path)} className={styles.btn}> 
                    <span>Shop</span>
                    <div className={styles.bthbefore}></div>
                </a>
            </div>
        </div>

    )
} 

export default positionMap