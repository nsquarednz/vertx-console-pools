<template>
    <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
        <div class="card-pf card-pf-view">
            <div class="card-pf-body">
                <div class="data-row">
                    <h2 class="card-pf-title pool-name">{{ pool.name }}</h2>
                    <div class="pool-type">{{ pool.type.toUpperCase() }}</div>
                </div>
                <div class="pf-card-separator" />
                <usage-map class="usage" :used="pool.inUse" :max="pool.maxSize" :largest="greatestSize" :queued="pool.queueSize" />
                <div class="pf-card-separator" />
                <div class="row rates">
                    <div class="col-sm-12 col-md-6 rate-col">
                        {{ pool.usage }}
                    </div>
                    <div class="col-sm-12 col-md-6 rate-col">
                        {{ pool.queueDelay }}
                    </div>
                </div>
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

.card-pf {
    padding: 0;
    border-left: none;
    border-right: none;
}

.card-pf-body {
    margin-top: 15px;
    .data-row,
    .usage,
    .rates {
        margin: 0 15px;
    }
}

.rates {
    .rate-col {
        padding: 0;

        &:last-child {
            padding-left: 5px;
        }
    }
}

.card-pf-body .pf-card-separator {
    height: 1px;
    background: #e1e1e1;
    margin: 15px 0;
}
</style>