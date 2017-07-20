<template>
    <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
        <div class="card-pf card-pf-view">
            <div class="card-pf-body">
                <div class="data-row">
                    <h2 class="card-pf-title pool-name">{{ pool.name }}</h2>
                    <div class="pool-type">{{ pool.type.toUpperCase() }}</div>
                </div>
                <usage-map :used="pool.inUse" :max="pool.maxSize" :largest="greatestSize" :queued="pool.queueSize" />
            </div>
        </div>
    </div>
</template>

<script>
import UsageMap from './UsageMap.vue';

export default {
    props: {
        pool: Object,
        greatestSize: Number
    },
    components: {
        'usage-map': UsageMap
    }
}
</script>

<style lang="scss" scoped>
.pool-name {
    font-size: 14px;
    margin: 0;
}

.pool-type {
    float: right;
    font-weight: bold;
}

.pool-name,
.pool-type {
    display: inline-block;
}
</style>