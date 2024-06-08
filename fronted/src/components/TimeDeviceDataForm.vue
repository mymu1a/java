<template>
  <div>
    <h1>Time Device Data</h1>
    <form @submit.prevent="addTimeDeviceData">
      <input v-model="deviceTime" placeholder="Time" required />
      <input v-model="timeConverter" placeholder="Converter ID" required />
      <button type="submit">Add</button>
    </form>
    <ul>
      <li v-for="timeDeviceData in timeDeviceData" :key="timeDeviceData.id">
        ID: {{ timeDeviceData.id }}, DeviceTime: {{ timeDeviceData.deviceTime }}
        <button @click="deleteTimeDeviceData(timeDeviceData.id)">Delete</button>
      </li>
    </ul>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      timeDeviceData: '',
      id: '',
      deviceTime: '',
      timeConverter: '',
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
        const response = await axios.post(`http://localhost:8080/time-devices/add-to-converter/${this.timeConverter}`, {
          deviceTime: this.deviceTime
        });
        this.deviceTime = '';
        this.timeConverter = '';
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
body {
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Helvetica, Arial, sans-serif, "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol";
  background-color: #24292e;
  color: #f0f6fc;
  margin: 0;
  padding: 0;
}

h1 {
  color: #f0f6fc;
  font-size: 32px;
  font-weight: 600;
  margin-bottom: 16px;
}

form {
  background-color: #1f1f1f; /* Darker form background */
  border: 1px solid #e1e4e8;
  border-radius: 6px;
  padding: 16px;
  margin-bottom: 24px;
}

input {
  border: 1px solid #e1e4e8;
  background-color: #2d333b; /* Darker input fields */
  border-radius: 6px;
  padding: 8px 12px;
  font-size: 16px;
  color: #f0f6fc;
  margin-right: 8px;
}

button {
  background-color: #2ea44f;
  border: 1px solid rgba(27, 31, 35, 0.15);
  border-radius: 6px;
  color: #ffffff;
  cursor: pointer;
  font-size: 16px;
  font-weight: 500;
  padding: 8px 16px;
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
  background-color: #1f1f1f; /* Darker list item background */
  border: 1px solid #e1e4e8;
  border-radius: 6px;
  padding: 16px;
  margin-bottom: 16px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

li button {
  margin-left: 16px;
  background-color: #d73a49;
  border: 1px solid rgba(27, 31, 35, 0.15);
  border-radius: 6px;
  color: #ffffff;
  cursor: pointer;
  font-size: 16px;
  font-weight: 500;
  padding: 8px 16px;
}
</style>