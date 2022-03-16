
import { useEffect } from 'react'
import styles from '../CSSstyle/shop.module.css'
import { AntibodyControllerStore, useAntibodyControllerStore } from '../Store/AntibodyControllerStore'
import { useDataStore } from '../Store/DataStore'
import { IoCloseSharp } from 'react-icons/io5';
import {  useShopStore } from '../Store/ShopStore'


export   const updateAntibodyController = (mouseindex : number[], index : number[]) => {
    AntibodyControllerStore.update(
        s => {
            s.status = !s.status
            s.locate.x = mouseindex[0]
            s.locate.y = mouseindex[1]
            s.locate.index[0] = index[0]
            s.locate.index[1] = index[1]
        }
    )
}


export const closecontroller = (arr :number[],max_x:number) => {
    const td = document.querySelectorAll('td')[arr[0]*max_x+arr[1]]
    td.style.cssText = "td{opacity: 1;transform: scale(1);transition: 0.1s ease-in-out;margin: 0;padding: 0;} td:hover{opacity: 0.5;transform: scale(1.2);}"
}


const AntibodyController = () => {
    const shopStore = useShopStore()
    const dataStore = useDataStore()
    const controllerStore = useAntibodyControllerStore()



    const notmove = (arr :number[])=>{
        closecontroller(controllerStore.locate.index,dataStore.max_x)
        updateAntibodyController([0,0], [0,0])
    }

    const cardmove = () => {
     
        const container = document.getElementById('container')
        
        if (container) {
            container.addEventListener('mousemove', (e : any) => {
                let xAxis = ((e.pageX - container.offsetLeft) - 30) / 2
                let yAxis = ((e.pageY - container.offsetTop) - 30) / 2
                container.style.cssText += `transform: rotateY(${xAxis}deg) rotateX(${yAxis}deg);`
            })
            container.addEventListener('mouseenter', (e : any) => {
                container.style.cssText += 'transition: none'
            })
            container.addEventListener('mouseleave', (e : any) => {
                container.style.cssText += 'transition: all 0.5s ease'
                container.style.cssText += `transform: rotateY(0deg) rotateX(0deg);`
            })
        }
    }

    useEffect(()=>{
        AntibodyControllerStore.update(s=>{
            s.status = false
        })
    },[dataStore.gameState])
    useEffect(() => {
        cardmove()
    }, [])
 
            

    return(
          <div id='container' className={controllerStore.status? styles.isOpen1 : styles.isnotOpen} 
          style={{top : `${controllerStore.locate.y-25}px`, left: `${controllerStore.locate.x-25}px`}}>
            <div id='card' className={styles.exit1}>
                <a onClick={() => {
                    notmove(shopStore.shopLocate.index)
                 }}><span><IoCloseSharp/></span></a>
            </div>
        </div>
    )
}

export default AntibodyController