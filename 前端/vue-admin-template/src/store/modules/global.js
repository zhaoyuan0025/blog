const state = {
  typeList: []
}

const mutations = {
  SET_TYPE: (state, typeList) => {
    state.typeList = typeList
  }
}

export default {
  namespaced: true,
  state,
  mutations
}
