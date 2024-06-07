<template>
  <div>
    <TimeDeviceDataForm />
  </div>
</template>

<script>
import TimeDeviceDataForm from '@/components/TimeDeviceDataForm.vue';

export default {
  name: 'TimeDeviceDataView',
  components: {
    TimeDeviceDataForm
  }
};
</script>

<style scoped>
div {
  padding: 20px;
}
</style>
