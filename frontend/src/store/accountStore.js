import { requestLogin } from "../common/api/accountAPI";

const state = {
  token: localStorage.getItem("ssafy-token"),
  id: localStorage.getItem("ssafy-id"),
};


const getters = {
  getToken: state => {
    return state.token;
  },
  getId: state => {
    return state.id;
  }
};

const mutations = {
  setToken: (state, token) => {
    state.token = token;
  },
  setId: (state, id) => {
    state.id = id;
  }
};

const actions = {
  loginAction: async ({ commit }, loginData) => {
    const response = await requestLogin(loginData);
    
    localStorage.setItem("ssafy-id", loginData.id);    
    localStorage.setItem("ssafy-token", response.data.accessToken);

    commit("setId", loginData.id);
    commit("setToken", response.data.accessToken);
  },

  logoutAction: ({ commit }) => {
    localStorage.removeItem("ssafy-id");
    localStorage.removeItem("ssafy-token");

    commit("setToken", null);
    commit("setId", null);
  }
};


export default {
  namespaced: true,
  state,
  getters,
  mutations,
  actions
};
