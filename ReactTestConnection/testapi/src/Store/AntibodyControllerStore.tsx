import {Store} from 'pullstate'

type AntibodyControllerStoreType = {
    status:boolean,
    locate:{
        x:number,
        y:number,
        index:number[]
    }
}

export const AntibodyControllerStore = new Store<AntibodyControllerStoreType>({
    status:false,
    locate:{
        x:0,
        y:0,
        index:[0,0]
    }
})

//custom hook
export function useAntibodyControllerStore():AntibodyControllerStoreType{  //use to pull data store element
    return AntibodyControllerStore.useState(s=>s)
}