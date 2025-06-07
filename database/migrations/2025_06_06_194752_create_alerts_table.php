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
        Schema::create('alerts', function (Blueprint $table) {
            $table->id();
            $table->foreignId('user_id')->constrained()->onDelete('cascade');
            $table->foreignId('measurement_id')->constrained()->onDelete('cascade');

            // Alert details
            $table->enum('type', ['critical', 'warning', 'info'])->default('info');
            $table->string('measurement_type'); // e.g., 'heart_rate', 'spo2', etc.
            $table->decimal('value', 8, 2); // The value that triggered the alert
            $table->decimal('threshold', 8, 2);
            $table->text('message');

            // Alert status
            $table->boolean('acknowledged')->default(false);
            $table->timestamp('acknowledged_at')->nullable();
            $table->foreignId('acknowledged_by')->nullable()->constrained('users')->onDelete('set null');

            $table->timestamps();

            // Indexes for performance (this is used for when showing alerts)
            $table->index(['user_id', 'acknowledged_at', 'created_at']);
            $table->index(['type', 'created_at']);
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('alerts');
    }
};
