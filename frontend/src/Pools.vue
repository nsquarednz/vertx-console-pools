
<template>
    <div class="container-fluid">
        <div class="page-header">
            <h1>Pool Statistics</h1>
        </div>
        <pool-card v-for="pool in basicPoolData" :key="pool.name" :pool="pool" :greatestSize="greatestSize" />
    </div>
</template>

<script>
import PoolCard from './PoolCard.vue';

const prefix = 'vertx.pools.';

export default {
    name: 'Pool Statistics',
    components: {
        'pool-card': PoolCard
    },
    data() {
        return {
            poolStats: {}
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
        greatestSize() {
            let maxSize = 0;
            for (let name of this.poolNames) {
                let size = this.getPoolProperty(name, 'max-pool-size');
                if (size && size.value > maxSize) {
                    maxSize = size.value;
                }
            }
            return maxSize;
        },
        basicPoolData() {
            const pools = [];
            for (let name of this.poolNames) {
                const dotIndex = name.indexOf('.');
                const type = name.substring(0, dotIndex);
                const id = name.substring(dotIndex + 1, name.length);

                let maxSizeProp = this.getPoolProperty(name, 'max-pool-size');
                let maxSize = undefined;
                let poolRatio = undefined;
                if (maxSizeProp) {
                    maxSize = maxSizeProp.value;
                    poolRatio = this.getPoolProperty(name, 'pool-ratio');
                }
                pools.push({
                    name: id,
                    type: type,
                    fullName: name,
                    maxSize: maxSize,
                    poolRatio: poolRatio.value,
                    inUse: this.getPoolProperty(name, 'in-use').count,
                    usage: this.getPoolProperty(name, 'usage'),
                    queueSize: this.getPoolProperty(name, 'queue-size').count,
                    queueDelay: this.getPoolProperty(name, 'queue-delay')
                })
            }
            return pools;
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