<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    /**
     * Run the migrations.
     */
    public function up(): void
    {
        Schema::create('measurements', function (Blueprint $table) {
            $table->id();
            $table->foreignId('user_id')->constrained()->onDelete('cascade');

            // Measurement data
            $table->integer('heart_rate')->nullable(); // BPM
            $table->integer('spo2')->nullable(); // Percentage %
            $table->integer('bp_systolic')->nullable(); // mmHg
            $table->integer('bp_diastolic')->nullable(); // mmHg
            $table->decimal('glucose', 5, 2)->nullable(); // mg/dL
            $table->decimal('temperature', 5, 2)->nullable(); // Celsius

            // Timestamps (recorded_at is different from the created_at timestamp --> Laravel default's)
            $table->timestamp('recorded_at'); // This column records when was the measurement first recorded/taken
            $table->timestamps(); // and for this Laravel default timestamp it records the updated at as well

            // Indexes for performance (This is used for when showing charts/trends)
            $table->index(['user_id', 'recorded_at']);
            $table->index('recorded_at');
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('measurements');
    }
};
