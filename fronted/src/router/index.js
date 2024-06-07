import { createRouter, createWebHistory } from 'vue-router';
import TimeConverterView from '@/views/TimeConverterView.vue';
import TimeDeviceDataView from '@/views/TimeDeviceDataView.vue';

const routes = [
    { path: '/', redirect: '/time-converter' },
    { path: '/time-converter', component: TimeConverterView },
    { path: '/time-device-data', component: TimeDeviceDataView },
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;
