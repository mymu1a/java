<template>
  <div id="app">
    <nav>
      <router-link to="/time-converter">Time Converter</router-link> |
      <router-link to="/time-device-data">Time Device Data</router-link>
    </nav>
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: 'App',
};
</script>

<style scoped>
nav {
  margin-bottom: 20px;
}
</style>
