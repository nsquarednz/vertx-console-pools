
<template>
    <div class="container-fluid">
        <div class="page-header">
            <h1>Pool Statistics</h1>
        </div>
        <pool-card v-for="name in poolNames" :key="name" :name="name" :poolStats="poolStats" />
    </div>
</template>

<script>
import PoolCard from './PoolCard.vue';

const prefix = 'vertx.pools.worker.';

export default {
    name: 'Pool Statistics',
    components: {
        'pool-card': PoolCard
    },
    data() {
        return {
            poolStats: {},
        }
    },
    computed: {
        poolNames() {
            const suffix = '.usage'
            const names = [];
            for (let key of Object.keys(this.poolStats)) {
                if (key.startsWith(prefix) && key.endsWith(suffix)) {
                    names.push(key.substring(prefix.length, key.length - suffix.length));
                }
            }
            return names.sort();
        },
        maxPoolSize() {
            let maxSize = 0;
            for (let name of this.poolNames) {
                let size = this.getPoolProperty(name, 'max-pool-size').value;
                if (size > maxSize) {
                    maxSize = size;
                }
            }
            return maxSize;
        }
    },
    methods: {
        getPoolProperty(name, prop) {
            return this.poolStats[prefix + name + '.' + prop];
        }
    },
    beforeMount() {
        const updateData = () => this.$http.get(window.location.pathname + '/pools')
            .then(response => response.json())
            .then(stats => this.poolStats = stats);
        updateData();
        this.updateTask = setInterval(updateData, 1000);
    },
    beforeDestroy() {
        clearInterval(this.updateTask);
    }
}
</script>