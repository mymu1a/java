<template>
  <div>
    <h1>Time Device Data</h1>
    <form @submit.prevent="addTimeDeviceData">
      <input v-model="time" placeholder="Time" required />
      <input v-model="converterId" placeholder="Converter ID" required />
      <button type="submit">Add</button>
    </form>
    <ul>
      <li v-for="deviceData in timeDeviceData" :key="deviceData.id">
        ID: {{ deviceData.id }}, Time: {{ deviceData.time }}, Converter ID: {{ deviceData.converterId }}
        <button @click="deleteTimeDeviceData(deviceData.id)">Delete</button>
      </li>
    </ul>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      time: '',
      converterId: '',
      timeDeviceData: []
    };
  },
  methods: {
    async fetchTimeDeviceData() {
      try {
        const response = await axios.get('http://localhost:8080/time-devices/all');
        this.timeDeviceData = response.data;
      } catch (error) {
        console.error('Failed to fetch time device data', error);
      }
    },
    async addTimeDeviceData() {
      try {
        const response = await axios.post('http://localhost:8080/time-devices/', {
          time: this.time,
          converterId: this.converterId
        });
        this.time = '';
        this.converterId = '';
        this.fetchTimeDeviceData();
      } catch (error) {
        console.error('Failed to add time device data', error);
      }
    },
    async deleteTimeDeviceData(id) {
      try {
        await axios.delete(`http://localhost:8080/time-devices/${id}`);
        this.fetchTimeDeviceData();
      } catch (error) {
        console.error('Failed to delete time device data', error);
      }
    }
  },
  mounted() {
    this.fetchTimeDeviceData();
  }
};
</script>

<style scoped>
h1 {
  color: green;
}
form {
  margin-bottom: 20px;
}
input {
  margin-right: 10px;
}
button {
  margin-left: 5px;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  margin: 10px 0;
}
</style>
