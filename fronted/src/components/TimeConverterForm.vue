<template>
  <div>
    <h1>Time Converter</h1>
    <form @submit.prevent="convertAndAddTime">
      <input v-model="milliseconds" placeholder="Milliseconds" required />
      <button type="submit">Add</button>
    </form>
    <ul>
      <li v-for="TimeConverter in TimeConverter" :key="TimeConverter.id">
        ID: {{ TimeConverter.id }}, localTime: {{ TimeConverter.localTime }}, gmtTime: {{ TimeConverter.gmtTime }}, timeDeviceDataList:{{ TimeConverter.timeDeviceDataList }}
        <button @click="deleteConverter(TimeConverter.id)">Delete</button>
      </li>
    </ul>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      TimeConverter: '',
      milliseconds: '',
      id: '',
      localTime: '',
      gmtTime: '',
      timeDeviceDataList: []
    };
  },
  methods: {
    async fetchConverters() {
      try {
        const response = await axios.get('http://localhost:8080/time-converter/');
        this.TimeConverter = response.data;
      } catch (error) {
        console.error('Failed to fetch converters', error);
      }
    },
    async convertAndAddTime() {
      try {
        // Call the convert endpoint first
        const convertResponse = await axios.get(`http://localhost:8080/date-convert`, {
          params: { milliseconds: this.milliseconds }
        });
        this.milliseconds = '';
        this.fetchConverters();
      } catch (error) {
        console.error('Failed to add converter', error);
      }
    },
    async deleteConverter(id) {
      try {
        await axios.delete(`http://localhost:8080/time-converter/${id}`);
        this.fetchConverters();
      } catch (error) {
        console.error('Failed to delete converter', error);
      }
    }
  },
  mounted() {
    this.fetchConverters();
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