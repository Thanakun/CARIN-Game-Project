import { useEffect, useState } from 'react'
import styles from '../CSSstyle/shop.module.css'

// icons
import { IoIosArrowDown } from 'react-icons/io';
import { IoCloseSharp } from 'react-icons/io5';

// Store
import { MapStore } from '../Store/MapStore'

// Store method
import { updatestatusShop } from '../Store/MapStore'

// images
import arrow from '../Images/whitearrowicon.png'

const Shop = () => {
    const state = MapStore.useState()

    const closeshop = (arr :number[]) => {
        const td = document.querySelectorAll('td')[arr[0]*state.x+arr[1]]
        td.style.cssText = "td{opacity: 1;transform: scale(1);transition: 0.1s ease-in-out;margin: 0;padding: 0;} td:hover{opacity: 0.5;transform: scale(1.2);}"
    
    }

    const cardmove = () => {
        // const card = document.getElementById('myShop')
        const container = document.getElementById('container')
        // console.log('card : ', card, " container : ", container)
        if (container) {
            container.addEventListener('mousemove', (e : any) => {
                console.log('W : ', e.pageX - container.offsetLeft)
                console.log('H : ', e.pageY - container.offsetTop)
                let xAxis = ((e.pageX - container.offsetLeft) - 124) / 5
                let yAxis = ((e.pageY - container.offsetTop) - 124) / 5
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

    useEffect(() => {
        cardmove()
    }, [])

    return(
        <div id='container' className={state.shopstatus? styles.isOpen1 : styles.isnotOpen} style={{top : `${state.shoplocate.y-125}px`, left: `${state.shoplocate.x-125}px`}}>
            <div id='card' className={styles.exit1}>
                <a onClick={() => {closeshop(state.shoplocate.index); updatestatusShop([0,0], [0,0]);}}><span><IoCloseSharp/></span></a>
            </div>
            <div className={styles.item1}>
                <div className={styles.TL}><IoIosArrowDown className={styles.iconAll}/></div>
                <div className={styles.T}><IoIosArrowDown className={styles.iconAll}/></div>
                <div className={styles.TR}><IoIosArrowDown className={styles.iconAll}/></div>
                <div className={styles.R}><IoIosArrowDown className={styles.iconAll}/></div>
                <div className={styles.BR}><IoIosArrowDown className={styles.iconAll}/></div>
                <div className={styles.B}><IoIosArrowDown className={styles.iconAll}/></div>
                <div className={styles.BL}><IoIosArrowDown className={styles.iconAll}/></div>
                <div className={styles.L}><IoIosArrowDown className={styles.iconAll}/></div>
            </div>
        </div>

        // อันเก่า
        // <div id='container' className={state.shopstatus? styles.isOpen2 : styles.isnotOpen} style={{top : `${state.shoplocate.y}px`, left: `${state.shoplocate.x}px`}}>
        // <div id='card' className={styles.exit2}>
        //     <a onClick={() => {closeshop(state.shoplocate.index); updatestatusShop([0,0], [0,0]);}}><span><IoCloseSharp/></span></a>
        // </div>
        // <div className={styles.item2}>
        //     <div className={styles.type1}>
        //         <a className={styles.type1name}>Type1</a>
        //         <a className={styles.type1price}>100</a>
        //     </div>
        //     <div className={styles.type2}>
        //         <a className={styles.type2name} onClick={() => {}}>Type2</a>
        //         <a className={styles.type2price} onClick={() => {}}>200</a>
        //     </div>
        //     <div className={styles.type3}>
        //         <a className={styles.type3name} onClick={() => {}}>Type3</a>
        //         <a className={styles.type3price} onClick={() => {}}>300</a>
        //     </div>
        // </div>
        // </div>
        
    )
}

export default Shop