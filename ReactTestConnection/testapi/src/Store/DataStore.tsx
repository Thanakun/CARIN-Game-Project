import {Store} from 'pullstate'
import { OrganismType, TimerType } from '../Components/Playing'


export type DataStoreType = {
    allOrganism: OrganismType[]
    max_x : number 
    max_y : number 
    timer : TimerType
    credit : number
    gameState: string
}

//custom hook
export function useDataStore():DataStoreType{  //use to pull data store element
    return DataStore.useState(s=>s)
}

export const DataStore = new Store<DataStoreType>({
    allOrganism: [],
    max_x: 10,
    max_y: 10,
    timer : {
        time_count : 0,
        timePass:100
    },
    credit : 0,
    gameState: "MainMenu"
})
