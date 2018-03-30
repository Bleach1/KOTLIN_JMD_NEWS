<!--1.模板-->
<template>
  <div class="Users">

    <!--4.使用属性--Parent-->
    <h1>{{title}}</h1>
    <ul>
      <li v-for="user in users">
        <router-link to="/">{{user.name}}--{{$router.name}}</router-link>
      </li>
    </ul>

  </div>
</template>
<!--2.处理逻辑-->
<script>

  import axios from 'axios'

  export default {

    name: "users",
    //3.设置属性--Parent
    props: {
      title: {
        type: String,
        required: true
      }
    },
    data() {
      return {
        users: ["A", "B", "C"]

      }
    },
    methods: {
      //1.注册事件--son
      changeContent: function () {
        this.$emit("changeContent", "子向父传值")
      },

    },
    beforeCreate: function () {
      // alert("组件实例化之前")
    },//loading,
    created: function () {
      // alert("组件实例化完毕，页面没显示")

      axios.get('http://jsonplaceholder.typicode.com/users').then((response) => {
        console.log(response.data);
        this.users = response.data;
      }).catch(function (error) {
        console.log(error)
      });
    },//获取数据,

    beforeMount: function () {
      // alert("组件挂载前，页面没显示，虚拟Dom已经配置")
    },
    mounted: function () {
      //  alert("组件挂载后，执行此方法页面显示")
    },//页面显示后操作
  }
</script>
<!--3.style-->
<!--scoped 作用于当前-->
<style scoped>
  h1 {
    color: red;
  }
</style>
