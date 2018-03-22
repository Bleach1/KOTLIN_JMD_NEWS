<template>

  <div id="app">
    <h1>Vue Project</h1>
    <img src="./assets/logo.png">
    <router-view/>
    <!--2.绑定属性--Parent-->
    <!--2.绑定属性--son-->
    <users v-on:changeContent="upDateContent($event)" v-bind:title="title"/>
  </div>
</template>

<script>
  import Users from './components/Users'
  import Banner from './components/Banner'

  export default {
    name: 'App',
    components: {
      "users": Users,
      "banner": Banner
    },
    methods: {
      upDateContent(content) {
//3.获取传值内容--son
      }
    },
    data() {
      return {
        //父组件属性值 传递给子
        //1.定义属性--Parent
        title: "属性传值Props"
      }
    }
  }
</script>

<style scoped>
  #app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    margin-top: 60px;
  }

  h1 {
    color: purple;
  }

</style>
