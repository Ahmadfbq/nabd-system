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
        Schema::create('health_profiles', function (Blueprint $table) {
            $table->id();

            $table->foreignId('user_id')->constrained()->onDelete('cascade');

            // Emergency contacts
            $table->string('emergency_contact_name')->nullable();
            $table->string('emergency_contact_phone')->nullable();
            $table->string('emergency_contact_email')->nullable();

            // Measurements thresholds for alerts
            $table->integer('heart_rate_min')->default(60);
            $table->integer('heart_rate_max')->default(100);
            $table->integer('spo2_min')->default(95);
            $table->integer('bp_systolic_max')->default(130);
            $table->integer('bp_diastolic_max')->default(80);
            $table->decimal('glucose_min', 5, 2)->default(70.0);
            $table->decimal('glucose_max', 5, 2)->default(126.0);
            $table->decimal('temperature_min', 4, 2)->default(36.1);
            $table->decimal('temperature_max', 4, 2)->default(37.2);
            $table->decimal('temperature_emergency', 4, 2)->default(41.0);

            // Settings
            $table->boolean('notifications_enabled')->default(true);
            $table->boolean('emergency_alerts_enabled')->default(true);

            $table->timestamps();
            $table->unique('user_id');
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('health_profiles');
    }
};
